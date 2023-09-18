import scrapy
from scrapy.linkextractors import LinkExtractor
from scrapy.spiders import CrawlSpider, Rule

from catguild_spider.items import Cat5dmVideo


class D5dmEndSpider(CrawlSpider):
    name = "d_5dm_end"
    allowed_domains = ["www.5dm.app"]
    start_urls = ["https://www.5dm.app/video/end/?orderby=date"]

    # 爬取的url的正则规则
    # https://www.5dm.app/video/end/page/2?orderby=date
    # https://www.5dm.app/video/end/?orderby=date
    rules = (
        Rule(
            LinkExtractor(allow=r"https://www.5dm.app/video/end/?(page/?\d+)?\?orderby=date"),
            callback="parse_item",
            follow=False
        ),
    )

    def parse_item(self, response):
        videos = response.xpath('//div[@class="item-thumbnail"]')
        for video in videos:
            # 解析提取 标题、封面、视频播放根地址
            cover_url = video.xpath('.//img/@data-original').extract_first()
            title = video.xpath('.//img/@alt').extract_first()
            video_root_url = video.xpath('.//a/@href').extract_first()
            cat_5dm_video = Cat5dmVideo(title=title, cover_url=cover_url, video_root_url=video_root_url)
            yield cat_5dm_video
