<#ftl encoding="UTF-8"/>
<#macro main title css=[] scripts=[]>
    <!DOCTYPE html>
    <html lang="ru" xmlns="http://www.w3.org/1999/html">
    <head>
        <meta charset="UTF-8">
        <title>${title}</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" type="text/css" href="/resources/static/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="/resources/css/main.css">
        <#list css as style>
            <link rel="stylesheet" type="text/css" href="/resources/css/${style}">
        </#list>
    </head>
    <body>
    <#include "header.ftl">
    <#nested>
    <#include "footer.ftl">
    <script src="/resources/static/jquery-3.5.1.min.js"></script>
    <script src="/resources/static/bootstrap.bundle.js"></script>
    <#list scripts as script>
        <script src="/resources/js/${script}"></script>
    </#list>
    </body>
    </html>
</#macro>