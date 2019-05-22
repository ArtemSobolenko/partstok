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
                        <label><input type="checkbox"
                                      name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>${role}
                        </label>
                    </div>
                </#list>
            </div>
            <div>
                <label >
                    <select name="discount">
                        <option disabled>Укажите скидку</option>
                        <option name="10" value="10">10%</option>
                        <option name="15" value="15">15%</option>
                        <option name="20" value="20">20%</option>
                        <option name="25" value="25">25%</option>
                    </select>
                </label>
            </div>
            <input type="hidden" name="userId" value="${user.id}">
            <button type="submit">Save</button>
        </form>
    </div>


</@c.page>