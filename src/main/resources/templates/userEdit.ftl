<#import "parts/common.ftl" as c>

<@c.page>
    <div>User Editor</div>
    <div>
        <form action="/user" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <input type="text" name="username" value="${user.username}">
            <div>
                <#list roles as role>
                    <div>
                        <label><input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>${role}</label>
                    </div>
                </#list>
            </div>
            <input type="hidden" name="userId" value="${user.id}">
            <button type="submit">Save</button>
        </form>
    </div>


</@c.page>