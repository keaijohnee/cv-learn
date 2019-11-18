# [OpenCV+TensorFlow 入门人工智能图像处理](https://www.bilibili.com/video/av51238267)

## 课程用到的库版本

+ Python3.7
+ TensorFlow 1.15.0
+ OpenCV 3.3.1

## 基础代码(Matplotlib+OpenCV进行图像处理和显示)

```python
from matplotlib import pyplot as plt
import cv2

# 读取图片。第一个参数是图片名称，第二个参数的图片颜色：cv2.IMREAD_GRAYSCALE=0是灰度图，cv2.IMREAD_COLOR=1是彩色图
img = cv2.imread('l00379880.png', cv2.IMREAD_COLOR)

# opencv默认的imread是以BGR的方式进行存储的,而matplotlib的imshow默认则是以RGB格式展示,所以此处我们必须对图片的通道进行转换
img = cv2.cvtColor(img, cv2.COLOR_BGR2RGB)
plt.imshow(img)

# 读取图片。第一个参数是图片名称，第二个参数的图片颜色：cv2.IMREAD_GRAYSCALE=0是灰度图，cv2.IMREAD_COLOR=1是彩色图
img_gray = cv2.imread('l00379880.png', cv2.IMREAD_GRAYSCALE)
img_gray = cv2.cvtColor(img_gray, cv2.COLOR_BGR2RGB)
plt.imshow(img_gray)

# 读取图片。第一个参数是图片名称，第二个参数的图片颜色：cv2.IMREAD_GRAYSCALE=0是灰度图，cv2.IMREAD_COLOR=1是彩色图
img_gray = cv2.imread('l00379880.png', cv2.IMREAD_GRAYSCALE)
img_gray = cv2.cvtColor(img_gray, cv2.COLOR_BGR2RGB)
plt.imshow(img_gray)
# 关闭坐标轴
plt.axis("off")
plt.show()
```