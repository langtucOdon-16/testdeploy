FROM tomcat:10.1

COPY dist/Project_PRJ301_Group3.war /usr/local/tomcat/webapps/ROOT.war

# đổi port Tomcat sang port Render cấp
RUN sed -i 's/port="8080"/port="${PORT}"/' /usr/local/tomcat/conf/server.xml
