#!/usr/bin/python3
import io
import sys
sys.stdout = io.TextIOWrapper(sys.stdout.buffer,encoding='utf-8')
print("Hello, World!")

import keyword
print(keyword.kwlist)

# string
regularStr = r"string start with r wont be affected by \ :\n"
print(regularStr)
print("System default encoding is " + sys.getdefaultencoding())
print("sys.stdout.encoding is: " + sys.stdout.encoding)
unicodeStr = "unicode字符串"
print(unicodeStr)
print("截取slice string 'abcd'[1:3]: ", 'abcd'[1:3])

# Vars
a, b = 0, 1
print('Value of a, b is: ', a, b)
print('Value of a, b (end with \`and\`) is: ', a, b, sep=' and ')

# 6 Types, Number, String, List, Tuple, Sets, Dictionaries
a, b, c, d = 20, 5.5, True, 4+3j # numbers
print('print number of types: ', type(a), type(b), type(c), type(d))
print('Math: // 除法得到整数 4//3= ', 4//3)
print('Math: ** 乘方 4**3= ', 4**3)

#list

# numpy
from numpy import *
print(eye(4))

# 本地settings重载
if os.path.isfile(os.path.join(os.path.dirname(__file__), 'gs_settings_local.py')):
    exec(open(os.path.join(os.path.dirname(__file__), 'gs_settings_local.py')).read())
