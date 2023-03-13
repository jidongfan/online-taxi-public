package com.fjd.servicemap.service;

import com.fjd.internalcommon.constant.AmapConfigConstants;
import com.fjd.internalcommon.constant.CommonStatusEnum;
import com.fjd.internalcommon.dto.DicDistrict;
import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.servicemap.mapper.DicDistrictMapper;
import com.fjd.servicemap.remote.MapDicDistrictClient;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/12 11:23
 * @desc:
 */
@Service
public class DicDistrictService {

    @Autowired
    MapDicDistrictClient mapDicDistrictClient;
    @Autowired
    DicDistrictMapper dicDistrictMapper;

    /**
     * 初始化地区信息
     * @param keywords
     * @return
     */
    public ResponseResult initDicDistrict(String keywords){

        //请求地图
        String dicDistrictResult = mapDicDistrictClient.dicDistrict(keywords);
        System.out.println(dicDistrictResult);

        //解析结果
        JSONObject dicDistrictJsonObject = JSONObject.fromObject(dicDistrictResult);
        int status = dicDistrictJsonObject.getInt(AmapConfigConstants.STATUS);
        // 1表示成功返回
        if(status != 1){
            return ResponseResult.fail(CommonStatusEnum.MAP_DISTRICT_ERROR.getCode(), CommonStatusEnum.MAP_DISTRICT_ERROR.getValue());
        }
        JSONArray countryJsonArray = dicDistrictJsonObject.getJSONArray(AmapConfigConstants.DISTRICTS);
        //第一层 国家
        for (int country = 0; country < countryJsonArray.size(); country++) {
            JSONObject countryJsonObject = countryJsonArray.getJSONObject(country);
            String countryAddressCode = countryJsonObject.getString(AmapConfigConstants.ADCODE);
            String countryAddressName = countryJsonObject.getString(AmapConfigConstants.NAME);
            String countryParentAddressCode = "0";
            String countryLevel = countryJsonObject.getString(AmapConfigConstants.LEVEL);

            insertDicDistrict(countryAddressCode, countryAddressName, countryParentAddressCode, countryLevel);

            //第二层 省级
            JSONArray provinceJsonArray = countryJsonObject.getJSONArray(AmapConfigConstants.DISTRICTS);
            for (int p = 0; p < provinceJsonArray.size(); p++) {
                JSONObject  provinceJsonObject = provinceJsonArray.getJSONObject(p);
                String provinceAddressCode = provinceJsonObject.getString(AmapConfigConstants.ADCODE);
                String provinceAddressName = provinceJsonObject.getString(AmapConfigConstants.NAME);
                String provinceParentAddressCode = countryAddressCode;
                String provinceLevel = provinceJsonObject.getString(AmapConfigConstants.LEVEL);

                insertDicDistrict(provinceAddressCode, provinceAddressName, provinceParentAddressCode, provinceLevel);

                //第三层 市
                JSONArray cityJsonArray = provinceJsonObject.getJSONArray(AmapConfigConstants.DISTRICTS);
                for (int c = 0; c < cityJsonArray.size(); c++) {
                    JSONObject  cityJsonObject = cityJsonArray.getJSONObject(c);
                    String cityAddressCode = cityJsonObject.getString(AmapConfigConstants.ADCODE);
                    String cityAddressName = cityJsonObject.getString(AmapConfigConstants.NAME);
                    String cityParentAddressCode = provinceAddressCode;
                    String cityLevel = cityJsonObject.getString(AmapConfigConstants.LEVEL);

                    insertDicDistrict(cityAddressCode, cityAddressName, cityParentAddressCode, cityLevel);

                    // 第四层 县区
                    JSONArray districtJsonArray = cityJsonObject.getJSONArray(AmapConfigConstants.DISTRICTS);
                    for (int d = 0; d < districtJsonArray.size(); d++) {
                        JSONObject  districtJsonObject = districtJsonArray.getJSONObject(d);
                        String districtAddressCode = districtJsonObject.getString(AmapConfigConstants.ADCODE);
                        String districtAddressName = districtJsonObject.getString(AmapConfigConstants.NAME);
                        String districtParentAddressCode = cityAddressCode;
                        String districtLevel = districtJsonObject.getString(AmapConfigConstants.LEVEL);

                        if(districtLevel.equals(AmapConfigConstants.STREET)){
                            continue;
                        }

                        insertDicDistrict(districtAddressCode, districtAddressName, districtParentAddressCode, districtLevel);

                    }
                }
            }
        }

        return ResponseResult.success("");
    }

    /**
     * 生成数据库对象 插入数据库
     * @param addressCode
     * @param addressName
     * @param parentAddressCode
     * @param level
     */
    public void insertDicDistrict(String addressCode, String addressName, String parentAddressCode, String level){
        //数据库对象
        DicDistrict dicDistrict = new DicDistrict();
        dicDistrict.setAddressCode(addressCode);
        dicDistrict.setAddressName(addressName);
        Integer levelInt = generateLevel(level);
        dicDistrict.setLevel(levelInt);

        dicDistrict.setParentAddressCode(parentAddressCode);
        //插入数据库
        dicDistrictMapper.insert(dicDistrict);
    }


    /**
     * 根据level级别，存储levelInt值
     * @param level
     * @return
     */
    public Integer generateLevel(String level){
        Integer levelInt = 0;
        if(level.trim().equals("country")){
            levelInt = 0;
        }else if(level.trim().equals("province")){
            levelInt = 1;
        }else if(level.trim().equals("city")){
            levelInt = 2;
        }else if(level.trim().equals("district")){
            levelInt = 3;
        }
        return levelInt;
    }
}
