cd C:\Users\liquancheng\OneDrive\myelipseWorkspace\overseas_infomation_crawler_github\src\cn\com\pengyue\crawler\spi

wsimport -d ./../../../../../../src/ -b building.xml -p cn.com.pengyue.vo.ws -wsdllocation http://localhost:8080/overseas_infomation/webservices?wsdl -Xnocompile http://localhost:8080/overseas_infomation/webservices?wsdl
pause
@echo 