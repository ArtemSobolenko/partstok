<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>
<div class="mb-1">Add new User</div>
<div>
    ${message?ifExists}
</div>
<@l.login "/registration" true/>
</@c.page>