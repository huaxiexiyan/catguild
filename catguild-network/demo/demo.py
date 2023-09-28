import pytz
import datetime

shanghai_timezone = pytz.timezone('Asia/Shanghai')
current_datetime = datetime.datetime.now(shanghai_timezone)
day = current_datetime.day

print(shanghai_timezone)
print(current_datetime)
print(day)
