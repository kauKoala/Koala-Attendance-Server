import time
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from bs4 import BeautifulSoup
from selenium.webdriver.chrome.options import Options
import boto3,json
from urllib.parse import quote
from selenium.webdriver.common.keys import Keys



def tistory_crawling():
    chrome_options = Options()
    chrome_options.add_argument('--headless')
    chrome_options.add_argument('--no-sandbox')
    chrome_options.add_argument('--disable-gpu')
    chrome_options.add_argument('--window-size=1280x1696')
    chrome_options.add_argument('--user-data-dir=/tmp/user-data')
    chrome_options.add_argument('--hide-scrollbars')
    chrome_options.add_argument('--enable-logging')
    chrome_options.add_argument('--log-level=0')
    chrome_options.add_argument('--v=99')
    chrome_options.add_argument('--single-process')
    chrome_options.add_argument('--data-path=/tmp/data-path')
    chrome_options.add_argument('--ignore-certificate-errors')
    chrome_options.add_argument('--homedir=/tmp')
    chrome_options.add_argument('--disk-cache-dir=/tmp/cache-dir')
    chrome_options.add_argument('user-agent=Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36')


    chrome_options.binary_location = "/opt/python/bin/headless-chromium"
    driver = webdriver.Chrome('/opt/python/bin/chromedriver', chrome_options=chrome_options)

    # 웹 페이지 열기
    driver.get("https://www.tistory.com/auth/login")  # 원하는 웹 페이지 URL로 변경
    time.sleep(30)  # 페이지 로딩을 기다리기 위한 대기시간 (실제 상황에 따라 조정 가능)

    login_button = driver.find_element(By.CLASS_NAME, "btn_login").click()

    time.sleep(30)


    # 아이디와 비밀번호 입력
    driver.find_element_by_id("loginId--1").send_keys("")  # 카카오 아이디
    driver.find_element_by_id("password--2").send_keys("")  # 카카오 비밀번호
    driver.find_element_by_class_name("btn_g").click()

    time.sleep(40)  # 페이지 로딩을 기다리기 위한 대기시간 (실제 상황에 따라 조정 가능)


    driver.get("https://kau-algorithm.tistory.com/manage/posts?category=1056752")
    time.sleep(40)  # 페이지 로딩을 기다리기 위한 대기시간 (실제 상황에 따라 조정 가능)


    # 원하는 데이터 추출
    ul_element = soup.find('ul', class_='list_post_type2')


    if ul_element:
        li_elements = ul_element.find_all('li')
        data_list = []
        time.sleep(5)
        for li in li_elements:
            li_data = {}

            link_cont = li.find("a", class_="link_cont")
            if link_cont:
                li_data["link_cont"] = link_cont.get_text()

            txt_info_txt_ellip = li.find("span", class_="txt_info txt_ellip")
            if txt_info_txt_ellip:
                li_data["txt_info_txt_ellip"] = txt_info_txt_ellip.get_text()

            txt_info = li.find("span", class_="txt_info")
            if txt_info:
                li_data["txt_info"] = txt_info.get_text()

            data_list.append(li_data)

        for data in data_list:
            print(data)

        driver.quit()
        return data_list
    else:
        print("No 'ul' element found on the page.")
        driver.quit()
        return None
