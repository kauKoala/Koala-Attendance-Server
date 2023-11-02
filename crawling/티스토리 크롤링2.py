import requests
from bs4 import BeautifulSoup

# 카카오 로그인 관련 URL
login_url = 'https://accounts.kakao.com/login'
redirect_url = 'https://www.acmicpc.net/after_login'  # 로그인 후 이동하고 싶은 페이지

# 로그인 데이터
payload = {
    "email": "20wjsdudtj@kau.kr",
    "password": "TEXTroad12",
}

# 세션 시작
with requests.Session() as session:
    # 로그인 페이지 열기
    login_page = session.get(login_url)

    # BeautifulSoup를 사용하여 HTML 파싱
    soup = BeautifulSoup(login_page.content, "html.parser")

    # 로그인 폼 데이터 가져오기
    login_form = soup.find("form", {"id": "login-form"})

    if login_form:
        # 로그인 데이터를 폼 데이터에 추가
        for key, value in payload.items():
            login_form.find("input", {"name": key})["value"] = value

        # 로그인 요청 보내기
        session.post(login_url, data=payload)

        # 로그인 후 페이지로 이동
        session.get(redirect_url)

        # 로그인 후 페이지의 HTML을 출력
        after_login_page = session.get(redirect_url)
        print(after_login_page.text)
    else:
        print("로그인 폼을 찾을 수 없습니다.")
