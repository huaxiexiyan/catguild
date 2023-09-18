# 解析百度百科文本内容
#
import re
from bs4 import BeautifulSoup

from simple_spider.pudong_school.spider.baike_spider import get_target_school_name
from simple_spider.pudong_school.spider.mongo_client import get_mongo_client

def has_numbers(string):
    return any(char.isdigit() for char in string)

def parse_html_content():
    db = get_mongo_client()
    collection = db["school"]
    collection.find_raw_batches()


def load_html(collection, excel_school_name):
    school = collection.find_one({"excel_school_name": excel_school_name})
    if school is not None:
        school_html = school.get("content_html")
        school_html = BeautifulSoup(school_html, 'lxml')
        school_html_content = school_html.find(name="div", attrs={"class": "content"})
        school_html_content_summary = school_html_content.find(name="div", attrs={"class": "para MARK_MODULE"})
        if school_html_content_summary is not None and has_numbers(school_html_content_summary.text):
            print(school_html_content_summary.text)
            # summary_text = school_html_content_summary.text
            # parse_area(r"([\\p{IsHan}]+面积)\\s?(\\d+)\\s?m2", summary_text)
            # parse_area(r"([\\p{IsHan}]+面积)\\s?(\\d+)\\s?平方米", summary_text)
            # parse_area(r"([\\p{IsHan}]+面积)\\s?(\\d+)\\s?平米", summary_text)
            # parse_area(r"([\\p{IsHan}]+面积)\\s?(\\d+)\\s?多平米", summary_text)
            # parse_area(r"([\\p{IsHan}]+面积)\\s?(\\d+)\\s?平方公里", summary_text)


def parse_area(pattern, summary_text):
    result = re.search(pattern, summary_text)
    if result:
        area_south = result.group(1)
        area_north = result.group(2)
        print(f"南区面积：{area_south}")
        print(f"北区面积：{area_north}")
    else:
        print("未找到匹配的面积信息")


if __name__ == '__main__':
    """
    1、读取采集的内容
    2、解析出content
    """
    school_name_list = get_target_school_name()
    print(school_name_list)
    db = get_mongo_client()
    collection = db["school"]
    list_size = len(school_name_list)
    # print("总记录数【{}】".format(list_size))

    # load_html(collection, "万科实验小学")

    for school_name in school_name_list:
        load_html(collection, school_name)
        list_size = list_size - 1
        print("还剩记录数【{}】".format(list_size))
