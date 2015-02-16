Sales Tax
=====================

To execute this specification, use

	gauge spec/sales_tax.spec

Basic Sales Tax
--------------
* Register Items <file:input1>
* Itemized receipt should contain
    | Quantity Description: Amount including tax |
    | 1 book: 12.49                              |
    | 1 music CD: 16.49                          |
    | 1 chocolate bar: 0.85                      |
* Total sales tax is "1.50"
* Total is "29.83"


Import Duty
--------------
* Register Items <file:input2>
* Itemized receipt should contain
    | Quantity Description: Amount including tax |
    | 1 imported box of chocolates: 10.50        |
    | 1 imported bottle of perfume: 54.65        |
* Total sales tax is "7.65"
* Total is "65.15"


Basic Sales Tax and Import Duty
--------------
* Register Items <file:input3>
* Itemized receipt should contain
    | Quantity Description: Amount including tax |
    | 1 imported bottle of perfume: 32.19        |
    |  1 bottle of perfume: 20.89                |
    |  1 packet of headache pills: 9.75          |
    |  1 box of imported chocolates: 11.85       |
* Total sales tax is "6.70"
* Total is "74.68"