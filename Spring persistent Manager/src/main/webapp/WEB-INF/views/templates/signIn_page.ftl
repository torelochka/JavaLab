<#ftl encoding='UTF-8'>
<#import 'layout/base.ftl' as base>
<@base.main title='SignIn'>
    <form method="post" action="/signIn">
        <input name="email">
        <input type="hidden" value="${_csrf_token}" name="_csrf_token">
        <input type="password" name="password">
        <input type="submit" value="SignIn">
    </form>
</@base.main>