<#import "parts/common.ftl" as c>

<@c.page>
    <div>
        List of reports
    </div>
    <div>
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th>Report ID</th>
                <th>User name</th>
                <th>Part name</th>
                <th>Part amount</th>
                <th>Is active</th>
                <th>Notify user</th>
                <th>Delete report</th>
            </tr>
            </thead>
            <tbody>
            <#list reports as report>
                <tr>
                    <td>${report.id}</td>
                    <td>${report.userReport.username}</td>
                    <td>${report.partReport.name}</td>
                    <td>${report.partReport.amount}</td>
                    <td>${report.active?string("Да", "Нет")}</td>
                    <td>
                        <#if report.partReport.amount gt 0 && report.active == true>
                            <a href="/report/notify/${report.id}">Notify via email</a><brt>
                            <a href="/report/bot/${report.id}">Notify via telegram</a>
                        </#if></td>
                    <td><#if report.active == false><a href="/report/delete/${report.id}">Delete</a></#if></td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</@c.page>