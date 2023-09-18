import scrapy

from catguild_spider.items import Cat5dmVideo


# 爬取 d 站
class D5dmSpider(scrapy.Spider):
    # 爬虫名称
    name = "d_5dm"
    # 爬取的域名范围
    allowed_domains = ["www.5dm.app"]
    # 完结番剧
    start_urls = ["https://www.5dm.app/video/end"]

    def parse(self, response):
        # 获取基本列表，每个div中有四个
        a_list = response.xpath('//div[@class="item-thumbnail"]')
        for a in a_list:
            # 解析提取 标题、封面、视频播放根地址
            cover_url = a.xpath('.//img/@data-original').extract_first()
            title = a.xpath('.//img/@alt').extract_first()
            video_root_url = a.xpath('.//a/@href').extract_first()
            cat_5dm_video = Cat5dmVideo(title=title, cover_url=cover_url, video_root_url=video_root_url)
            yield cat_5dm_video
            # 如果传递参数，使用 meta 字典传递
