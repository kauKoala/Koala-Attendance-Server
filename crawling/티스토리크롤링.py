from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC


# 크롬 드라이버 경로 설정
chrome_options = webdriver.ChromeOptions()
chrome_options.binary_location = '/usr/bin/google-chrome'  # 크롬 바이너리 위치
driver = webdriver.Chrome(options=chrome_options)

# 웹 페이지 열기
driver.get("https://www.tistory.com/auth/login")  # 원하는 웹 페이지 URL로 변경

# "카카오계정 로그인" 버튼을 찾아 클릭
login_button = driver.find_element(By.CLASS_NAME, "btn_login")

login_button.click()


print(driver.page_source)


# 웹 브라우저 닫기 (선택적)
driver.quit()
