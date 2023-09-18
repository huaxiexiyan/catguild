from selenium import webdriver
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.by import By

driver = webdriver.Chrome()
# 打开百度百科首页：使用 WebDriver 实例打开百度百科首页
driver.get("https://baike.baidu.com/")

# 定位输入框并输入文字
input_box = driver.find_element(By.ID, "query")
input_box.send_keys("上海市黄楼中学")
# 点击输入框来触发下拉框的出现
input_box.click()

# 等待下拉框出现并选择第一个候选项
wait = WebDriverWait(driver, 10)
dropdown = wait.until(EC.visibility_of_element_located((By.CLASS_NAME, "sug-lemma_item")))
dropdown.click()

# 点击进入词条按钮
button = driver.find_element(By.ID, "search")
button.click()
# 获取结果网页
wait.until(EC.visibility_of_element_located((By.CLASS_NAME, "content")))
result_page = driver.page_source

print(result_page)

# 关闭 WebDriver 实例
driver.quit()
