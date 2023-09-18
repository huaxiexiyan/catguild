from urllib.parse import quote_plus

import pymongo

def get_mongo_client():
    client = pymongo.MongoClient(
        "mongodb://%s:%s@%s" % (quote_plus(""), quote_plus(""), ""))
    return client["catguild_prod"]
