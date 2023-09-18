import json

import scrapy


# 爬取 d 站
class TestPostSpider(scrapy.Spider):
    name = "test_post"
    allowed_domains = ["fanyi.baidu.com"]

    def start_requests(self):
        # post 请求必须使用 start_requests 起始
        url = 'https://fanyi.baidu.com/sug'
        data = {
            'kw': 'final'
        }
        yield scrapy.FormRequest(url=url, formdata=data, callback=self.parse_second)

    def parse_second(self, response):
        content = response.text
        obj = json.loads(content)
        print(obj)
