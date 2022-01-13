class Calculator:
    def __init__(self, first, second):
        self.first = first
        self.second = second

    def setdata(self, first, second):
        self.first = first
        self.second = second
    
    # 덧셈 정의
    def add(self):
        result = self.first + self.second
        return result
    
    # 뺄셈 정의
    def sub(self):
        result = self.first - self.second
        return result

		# 곱셈 정의
    def mul(self):
        result = self.first * self.second
        return result
    
    # 나눗셈 정의
    def div(self):
        result = self.first / self.second
        return result

cal1 = Calculator(4, 0)
print(cal1.div())