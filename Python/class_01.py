# class를 활용해 계산기 만들기

class Calculator1:
    def __init__(self):
        self.value = 0
    
    # 덧셈 정의
    def cal_add(self, num):
        self.value += num
        return self.value
    
    # 뺄셈 정의
    def cal_minus(self, num):
        self.value -= num
        return self.value
    
cal1 = Calculator1()
print(cal1.cal_add(100))
print(cal1.cal_minus(95))

cal2 = Calculator1()
print(cal2.cal_minus(10))

# 상속
class Calculator2(Calculator1):
    def __init__(self):
        self.value = 0

    # 곱셈 정의
    def cal_product(self, num):
        self.value *= num
        return self.value
    
    # 나눗셈 정의
    def cal_divide(self, num):
        self.value /= num
        return self.value

cal3 = Calculator2()
print(cal3.cal_add(10))
print(cal3.cal_product(3))
print(cal3.cal_divide(3))