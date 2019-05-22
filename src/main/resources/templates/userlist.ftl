<#import "parts/common.ftl" as c>

<@c.page>
    <div>
        List of users
    </div>
    <div>
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th>Name</th>
                <th>Role</th>
                <th>Discount</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            </thead>
            <tbody>
            <#list users as user>
                <tr>
                    <td>${user.username}</td>
                    <td><#list user.roles as role>${role}<#sep>, </#list></td>
                    <td>${user.discount}</td>
                    <td><a href="/user/${user.id}">Edit</a></td>
                    <td><a href="parts/deleteUser?userId=${user.id}">Delete</a></td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</@c.page>