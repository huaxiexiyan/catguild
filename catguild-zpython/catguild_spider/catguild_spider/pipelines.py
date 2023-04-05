# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: https://docs.scrapy.org/en/latest/topics/item-pipeline.html
import re

# useful for handling different item types with a single interface
from itemadapter import ItemAdapter
import urllib.request


class CatguildSpiderPipeline:
    # 爬虫运行前后执行
    def open_spider(self, spider):
        self.fp = open('.\\5dm.json', 'w', encoding='utf-8')

    def process_item(self, item, spider):
        self.fp.write(str(item))
        return item

    # 爬虫运行完毕后执行
    def close_spider(self, spider):
        self.fp.close()


# 下载封面图片
class DownLoadCoverPipeline:
    def process_item(self, item, spider):
        url = item.get('cover_url')
        file_path = './images/' + item.get('title') + '.jpg'
        file_path = re.sub('【.*?】', '', file_path).replace('!', '')
        urllib.request.urlretrieve(url=url, filename=file_path)
        return item
