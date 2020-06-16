package cn.van.jdk.tool.math;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CoordinateTransformUtilTest {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 指定经纬度是否在国外
     */
    @Test
    public void outOfChina() {
        // 北京经纬度
        double lngBJ = 115.25;
        double latBJ = 39.526128;
        logger.info("result:{}", CoordinateTransformUtil.outOfChina(lngBJ, latBJ));
        // 东京经纬度
        double lngDJ = 139.4;
        double latDJ = 35.4;
        logger.info("result:{}", CoordinateTransformUtil.outOfChina(lngDJ, latDJ));
    }

    /**
     * 指定经纬度是否在国外
     */
    @Test
    public void convertLatLonByCoordinate() {
        String newCoordinateType = "baidu";
        String originalCoordinateType = "wgs84";
        double lat = 39.526128;
        double lng = 115.25;
        logger.info("result:{}", CoordinateTransformUtil.convertLatLonByCoordinate(newCoordinateType, originalCoordinateType, lng, lat));
    }
}
