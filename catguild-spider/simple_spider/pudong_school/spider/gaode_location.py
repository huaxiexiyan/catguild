import hashlib
import pyproj
import requests

from simple_spider.pudong_school.spider.baike_spider import get_target_school_name, get_mongo_client


def get_location(school_name):
    r"""
    将地址换化为坐标
    :return:
    """
    # 定义URL和参数
    url = "https://restapi.amap.com/v3/geocode/geo"
    params = {"key": "", "address": school_name, "city": "310115", "output": "JSON"}
    # 发送GET请求
    return requests.get(url, params=params).json()


def store_school_location(collection, school_name):
    location_json = get_location(school_name)
    print("响应结果:【{}】".format(location_json))
    if location_json['status'] == '1':
        md5_hash = hashlib.md5()
        md5_hash.update(school_name.encode('utf-8'))
        data = {"id": md5_hash.hexdigest(),
                "excel_school_name": school_name,
                "location_json": location_json}
        collection.insert_one(data)
    else:
        print("请求失败：【{}】".format(school_name))

def convert_coordinates(locations):
    url = "https://restapi.amap.com/v3/geocode/geo"
    params = {"key": "", "locations": locations, "coordsys": "310115", "output": "JSON"}
    # 发送GET请求
    return requests.get(url, params=params).json()

def gcj02_to_wgs84(gcj_lng, gcj_lat):
    """
    :param gcj_lng: 高德经度
    :param gcj_lat: 高德纬度
    :return:
    """
    # 定义高德坐标系和WGS 84坐标系的投影转换器
    gcj_proj = pyproj.Proj(proj='merc', datum='WGS84', ellps='WGS84', a=6378137.0, rf=298.257223563)
    wgs84_proj = pyproj.Proj(proj='latlong', datum='WGS84', ellps='WGS84', a=6378137.0, rf=298.257223563)

    # 创建转换器
    transformer = pyproj.Transformer.from_proj(gcj_proj, wgs84_proj)

    # 进行坐标转换
    wgs84_lng, wgs84_lat = transformer.transform(gcj_lng, gcj_lat)

    return wgs84_lng, wgs84_lat

def gcj_to_wgs(gcj_lat, gcj_lng):
    gcj_proj = pyproj.Proj(proj='latlong', datum='WGS84')
    wgs_proj = pyproj.Proj(proj='latlong', datum='WGS84')
    wgs_lng, wgs_lat = pyproj.transform(gcj_proj, wgs_proj, gcj_lng, gcj_lat)
    return wgs_lat, wgs_lng


if __name__ == '__main__':
    gcj_lat, gcj_lng = 121.670372, 31.161694
    wgs_lat, wgs_lng = gcj_to_wgs(gcj_lat, gcj_lng)
    # print("WGS-84 Coordinates:", wgs_lat, wgs_lng)

    # school_name_list = get_target_school_name()
    # print(school_name_list)
    # db = get_mongo_client()
    # collection = db["school_location"]
    # list_size = len(school_name_list)
    # print("总记录数【{}】".format(list_size))
    # for school_name in school_name_list:
    #     print("准备下载html【{}】".format(school_name))
    #     store_school_location(collection, school_name)
    #     time.sleep(3)
    #     print("暂停3s后继续")
    #     list_size = list_size - 1
    #     print("还剩记录数【{}】".format(list_size))
    # print(gcj02_to_wgs84("121.670372","31.161694"))
    # print(gcj02_to_wgs84("31.161694","121.670372"))
    # print(get_location("上海市浦东新区栏学路495号"))
#     121.542067,31.365498
#     121.542259,31.365168
