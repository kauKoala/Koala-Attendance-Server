
from urllib.request import Request, urlopen
from bs4 import BeautifulSoup

url = 'https://www.acmicpc.net/user/20wjsdudtj'

problem_num = []
YesorYes = []

req = Request(url=url, headers={'User-Agent': 'Mozilla/5.0'})
response = urlopen(req).read()


# 성공적으로 data(html)를 받아왔다면
print("Success!!")

# BeautifulSoup을 사용하여 data를 파싱합니다.
soup = BeautifulSoup(response, 'html.parser')
print(soup.prettify())


# 해당 html중 <tbody> 태그의 <tr> 태그의 3번째 <td> 태그의 <a> 태그의 href 를 가져옵니다.
for item in soup.select('div.panel-default:nth-of-type(2) > div.panel-body > div.problem-list > a'):
    print(item)
    problem_num.append(item.text)  # 문제 번호를 저장하기


print(problem_num)

