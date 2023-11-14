import re

f = open("/home/eugene131/personal/out.txt","r")

text = f.read().split('\n')
text.sort()
print(text)
f.close()
text_lst = []
for i in text:
  temp = i.split("의 퍼스널 컬러는 ")
  text_lst.append(temp)
#print(text_lst)
sorted
for i in range(1,len(text_lst)):
  text_lst[i][0] = text_lst[i][0].split("/")[5]
  text_lst[i][0] = text_lst[i][0][:-4]
  text_lst[i][1] = text_lst[i][1][:2]
text_lst.pop(0)

def extract_number(element):
    # 정규 표현식을 사용하여 텍스트에서 숫자 값을 추출합니다.
    numbers = re.findall(r'\d+', element[0])
    # 숫자 값을 정수로 변환하여 반환합니다.
    return int(numbers[0]) if numbers else 0

text_lst = sorted(text_lst, key=extract_number)
print(text_lst)


f = open("/home/eugene131/personal/out.txt","w")

for i in text_lst:
  f.write(i[0]+"\t"+i[1]+"\n")
print(text_lst)