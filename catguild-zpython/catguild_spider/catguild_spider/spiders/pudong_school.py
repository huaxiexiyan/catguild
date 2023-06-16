import scrapy
from catguild_spider.items import Cat5dmVideo
from scrapy.linkextractors import LinkExtractor
from scrapy.spiders import CrawlSpider, Rule


class PudongSchoolSpider(CrawlSpider):
    name = "pudong_school"
    allowed_domains = ["https://www.pudong.gov.cn"]
    start_urls = ["https://www.pudong.gov.cn/pudong-interaction-front/edu/school/detail?schoolId=00e0cdcce852427a9f6684a9c62adeb5"]

    def start_requests(self):
        yield scrapy.Request(
            url='https://www.pudong.gov.cn/pudong-interaction-front/edu/school/detail?schoolId=00e0cdcce852427a9f6684a9c62adeb5',
            headers={
                'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7',
                'Accept-Encoding': 'gzip, deflate, br',
                'Accept-Language': 'zh-CN,zh;q=0.9',
                'Cache-Control': 'no-cache',
                'Connection': 'keep-alive',
                'Cookie': 'https://www.pudong.gov.cn/pudong-interaction-front/edu/school/detail?schoolId=00e0cdcce852427a9f6684a9c62adeb5',
                'Host': 'www.pudong.gov.cn',
                'Pragma': 'no-cache',
                'Referer': 'https://www.pudong.gov.cn/pudong-interaction-front/edu/school/detail?schoolId=00e0cdcce852427a9f6684a9c62adeb5',
                'Sec-Fetch-Dest': 'document',
                'Sec-Fetch-Mode': 'navigate',
                'Sec-Fetch-Site': 'same-origin',
                'Sec-Fetch-User': '?1',
                'Upgrade-Insecure-Requests': '1',
                'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36',
                'sec-ch-ua': '"Not.A/Brand";v="8", "Chromium";v="114", "Google Chrome";v="114"',
                'sec-ch-ua-mobile': '?0',
                'sec-ch-ua-platform': '"Windows"'
            },
            callback=self.parse
        )

    def parse(self, response):
        # 获取基本列表，每个div中有四个
        a_list = response.xpath('//div[@class="ewb-result-grap"]//a[@class="ewb-result-img"]')
        for a in a_list:
            # 解析提取 标题、封面、视频播放根地址
            span = a.xpath('.//span/text()').extract_first()
            title = a.xpath('.//img/@alt').extract_first()
            detail_url = a.xpath('./@href').extract_first()
            print('title=', title)
            print('span', span)
            print('url=', detail_url)
            # 如果传递参数，使用 meta 字典传递


