# 제네레이터 (generator)

def generator():
    yield 9
    cnt = 0
    for i in range(10):
        cnt += 1
    print(cnt)
    yield 'algoant'
    yield True

gen = generator()
print(gen)

while True:
    print(next(gen))