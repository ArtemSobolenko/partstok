<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>
<div>Add new User</div>
<div>
    ${message?ifExists}
</div>
<@l.login "/registration" />
</@c.page>