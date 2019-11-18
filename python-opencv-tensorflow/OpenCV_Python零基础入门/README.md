# OpenCV Python零基础入门

## 参考博客

+ [使用python开启你的opencv之旅---图像的读入,存储](https://www.cnblogs.com/lynsyklate/p/7720045.html)

## OpenCV和Matplotlib实现图像处理结果展示

```python
from matplotlib import pyplot as plt
import cv2

def show(img_path):
    """
    读取彩色图像
    """
    img = cv2.imread(img_path, cv2.IMREAD_COLOR)
    # opencv默认是BGR存储图像,正常是RGB，所以需要进行转换
    img = cv2.cvtColor(img, cv2.COLOR_BGR2RGB) 
    # 关闭坐标轴显示
    plt.axis("off")
    plt.imshow(img)

def show_img(img):
    """
    显示彩色图像
    """
    img = cv2.cvtColor(img, cv2.COLOR_BGR2RGB) 
    # 关闭坐标轴显示
    plt.axis("off")
    plt.imshow(img)

def show_gray(img_path):
    """
    读取灰度图像
    """
    img = cv2.imread(img_path, cv2.IMREAD_GRAYSCALE)
    # opencv默认是BGR存储图像,正常是RGB，所以需要进行转换
    img = cv2.cvtColor(img, cv2.COLOR_BGR2RGB) 
    # 关闭坐标轴显示
    plt.axis("off")
    plt.imshow(img)
```