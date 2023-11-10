import time
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from bs4 import BeautifulSoup


# 크롬 드라이버 경로 설정
chrome_options = webdriver.ChromeOptions()
chrome_options.binary_location = '/usr/bin/google-chrome'  # 크롬 바이너리 위치
driver = webdriver.Chrome(options=chrome_options)

# 웹 페이지 열기
driver.get("https://www.tistory.com/auth/login")  # 원하는 웹 페이지 URL로 변경

# "카카오계정 로그인" 버튼을 찾아 클릭
login_button = driver.find_element(By.CLASS_NAME, "btn_login")

login_button.click()

# 아이디와 비밀번호 입력
id_input = driver.find_element(By.ID, "loginId--1")  
id_input.send_keys("")  # 카카오 아이디

password_input = driver.find_element(By.ID, "password--2")  
password_input.send_keys("")  # 카카오 비밀번호 입력

time.sleep(1)

# 로그인 버튼 클릭
login_button = driver.find_element(By.CLASS_NAME, "btn_g")  # 클래스 이름으로 버튼 찾기
login_button.click()
time.sleep(2)

# 로그인 후 리디렉션
driver.get("https://kau-algorithm.tistory.com/manage/posts?category=1056752")

time.sleep(2)

# ul 요소 선택
ul_element = driver.find_element(By.CLASS_NAME, "list_post_type2")

# BeautifulSoup로 파싱
soup = BeautifulSoup(ul_element.get_attribute("outerHTML"), 'html.parser')

# 모든 li 요소 추출
li_elements = soup.find_all("li")
# 리스트 초기화
data_list = []
for li in li_elements:
    li_data = {}

    # link_cont 클래스를 가진 a 요소 추출
    link_cont = li.find("a", class_="link_cont")
    if link_cont:
        li_data["link_cont"] = link_cont.get_text()

    # txt_info txt_ellip 클래스를 가진 span 요소 추출
    txt_info_txt_ellip = li.find("span", class_="txt_info txt_ellip")
    if txt_info_txt_ellip:
        li_data["txt_info_txt_ellip"] = txt_info_txt_ellip.get_text()

    # txt_info 클래스를 가진 span 요소 추출
    txt_info = li.find("span", class_="txt_info")
    if txt_info:
        li_data["txt_info"] = txt_info.get_text()

    data_list.append(li_data)

# 리스트 출력
for data in data_list:
    print(data)

# 마무리
driver.quit()