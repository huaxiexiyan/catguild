# Define here the models for your scraped items
#
# See documentation in:
# https://docs.scrapy.org/en/latest/topics/items.html

import scrapy


class Cat5dmVideo(scrapy.Item):
    # define the fields for your item here like:
    # name = scrapy.Field() 电影
    # 番剧封面、标题、链接地址
    title = scrapy.Field()
    cover_url = scrapy.Field()
    video_root_url = scrapy.Field()

