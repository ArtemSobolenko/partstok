<#import "parts/common.ftl" as c>
<#import "parts/pager.ftl" as p>
<#include "parts/security.ftl">

<@c.page>
    <form method="get" action="/part">
        <div class="custom-control custom-radio custom-control-inline">
            <input class="custom-control-input" type="radio" name="filter" id="Radios1" value="true">
            <label class="custom-control-label" for="Radios1">Обязательно для зборки</label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input class="custom-control-input" type="radio" name="filter" id="Radios2" value="false">
            <label class="custom-control-label" for="Radios2">Небязательно для зборки</label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input class="custom-control-input" type="radio" name="filter" id="Radios3" value="all">
            <label class="custom-control-label" for="Radios3">Все</label>
        </div>
        <button class="btn btn-primary mb-2" type="submit">Фильтровать</button>
    </form>
    <div>
        <form method="get" action="search">
            <#--            <input type="hidden" name="_csrf" value="${_csrf.token}">-->
            <input class="form-control col-sm-2 mb-2" type="text" name="search" placeholder="поиск">
            <button class="btn btn-primary mb-2" type="submit">Поиск</button>
        </form>
    </div>
    <a class="btn btn-primary mb-2" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"
       aria-controls="collapseExample">
        Add new part
    </a>
    <div class="collapse <#if part??>show</#if>" id="collapseExample">
        <div class="form-group mt-2">
            <form method="post" enctype="multipart/form-data">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <div class="form-group">
                    <input class="form-control col-sm-5 ${(nameError??)?string('is-invalid', '')}" type="text"
                           value="<#if part??>${part.name}</#if>"
                           name="name" placeholder="Наименование детали">
                    <#if nameError??>
                        <div class="invalid-feedback">
                            ${nameError}
                        </div>
                    </#if>
                </div>
                <div class="form-group">
                    <input class="form-control col-sm-2 ${(amountError??)?string('is-invalid', '')}" type="text"
                           name="amount" placeholder="Количество">
                    <#if amountError??>
                        <div class="invalid-feedback">
                            ${amountError}
                        </div>
                    </#if>
                </div>
                <div class="form-group">
                    <input class="form-control col-sm-2 ${(priceError??)?string('is-invalid', '')}" type="text"
                           name="price" placeholder="price">
                    <#if priceError??>
                        <div class="invalid-feedback">
                            ${priceError}
                        </div>
                    </#if>
                </div>
                <div class="custom-control custom-checkbox">
                    <input type="checkbox" class="custom-control-input" id="customCheck1" name="need">
                    <label class="custom-control-label" for="customCheck1">Необходимость для зборки</label>
                </div>
                <div class="custom-file">
                    <input class="custom-file-input" type="file" name="file" id="customFile">
                    <label class="custom-file-label col-sm-5" for="customFile">Choose file</label>
                </div>
                <button class="btn btn-primary mt-2" type="submit">Добавить</button>
            </form>
        </div>
    </div>
    <@p.pager url page/>
    <div class="card-columns">
        <#list page.content as part>
            <div class="card my-3">
                <div>
                    <#if part.filename??>
                        <img src="/img/${part.filename}" class="card-img-top">
                    </#if>
                </div>
                <div class="m-2">
                    <span>Номер: ${part.id}</span><br>
                    <span>Наименование: ${part.name}</span><br>
                    <span>Необходимость для сборки: ${part.need?string("Да", "Нет")}</span><br>
                    <span>Количество: ${part.amount}</span><br>
                    <span class="d-inline-block" tabindex="0" data-toggle="tooltip" title="Disabled tooltip">
                        <button class="btn btn-primary my-3" style="pointer-events: none;" type="button" disabled>Price: ${part.price-(part.price*user.discount)/100}$</button>
                    </span>
                </div>
                <div class="card-footer text-muted">
                    ${part.ownerName}
                </div>
                <#if isAdmin>
                    <div>
                        <a class="btn btn-primary" href="parts/partEdit?partId=${part.id}">Edit</a>
                    </div>

                    <div>
                        <a class="btn btn-danger" href="parts/partDelete?partId=${part.id}">Delete</a>
                    </div>
                </#if>
            </div>
        <#else >
            <div>No parts</div>
        </#list>
    </div>
    <@p.pager url page/>
</@c.page>