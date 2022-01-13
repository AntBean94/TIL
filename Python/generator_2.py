# generator (2) - 이점

import sys
a = [i for i in range(100000)]
print(type(a))
print(sys.getsizeof(a))
print(len(a))
# print(a)
b = (i for i in range(100000))
print(type(b))
print(sys.getsizeof(b))
print(type(range))
print(len(list(b)))
print(len(list(b)))
# for i in b:
#     print(i)
# print(b[10])