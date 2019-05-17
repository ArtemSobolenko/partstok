<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
<div>Login page</div>
<@l.login "/login" />
<div><a href="/registration">Add new user</a> </div>
</@c.page>