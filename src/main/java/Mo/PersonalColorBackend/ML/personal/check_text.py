f = open("/home/eugene131/personal/out.txt","r")
lst_ = []
full_text = f.read()
print("가을웜톤 갯수: ",full_text.count('가을웜톤'))

print("봄웜톤 수: ",full_text.count('봄웜톤'))

print("여름:",full_text.count('여름쿨톤'))

print("겨울:",full_text.count('겨울쿨톤'))