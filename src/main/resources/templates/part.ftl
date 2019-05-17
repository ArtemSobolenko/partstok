<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>
    <div>
        <@l.logout/>
        <span><a href="/user">User list</a> </span>
    </div>
    <div>
        <form method="get" action="/part">
            <span><input type="radio" name="filter" value="true">Надо</span>
            <span><input type="radio" name="filter" value="false">Не надо</span>
            <span><input type="radio" name="filter" value="all">Все</span>
            <button type="submit">Найти</button>
        </form>
    </div>
    <div>
        <div>Список деталей</div>
        <#list parts as part>
            <div>
                <span>${part.id}</span>
                <span>${part.name}</span>
                <span>${part.need?string("yes", "no")}</span>
                <span>${part.amount}</span>
                <strong>${part.ownerName}</strong>
            </div>
        <#else >
            <div>No parts</div>
        </#list>
    </div>
    <div>
        <form method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <input type="text" name="name" placeholder="Наименование детали">
            <input type="text" name="amount" placeholder="Количество">
            <input type="checkbox" name="need"><span>Необходимость</span>
            <button type="submit">Добавить</button>
        </form>
    </div>
</@c.page>