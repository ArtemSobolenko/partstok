<#import "parts/common.ftl" as c>
<#import "parts/pager.ftl" as p>

<@c.page>
    <form method="get" action="/part">
        <div class="custom-control custom-radio custom-control-inline">
            <input class="custom-control-input" type="radio" name="filter" id="Radios1" value="true">
            <label class="custom-control-label" for="Radios1">Надо</label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input class="custom-control-input" type="radio" name="filter" id="Radios2" value="false">
            <label class="custom-control-label" for="Radios2">Не надо</label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input class="custom-control-input" type="radio" name="filter" id="Radios3" value="all">
            <label class="custom-control-label" for="Radios3">Все</label>
        </div>
        <button class="btn btn-primary mb-2" type="submit">Фильтровать</button>
    </form>
    <a class="btn btn-primary mb-2" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"
       aria-controls="collapseExample">
        Add new part
    </a>
    <div class="collapse" id="collapseExample">
        <div class="form-group mt-2">
            <form method="post" enctype="multipart/form-data">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <div class="form-group">
                    <input class="form-control col-sm-5 ${(textError??)?string('is-invalid', '')}" type="text"
                           value="<#if part??>${part.name}</#if>"
                           name="name" placeholder="Наименование детали">
                    <#if textError??>
                        <div class="invalid-feedback">
                            ${textError}
                        </div>
                    </#if>
                </div>
                <div class="form-group">
                    <input class="form-control col-sm-2" type="text" name="amount" placeholder="Количество">
                </div>
                <#--                <input type="checkbox" name="need"><span>Необходимость</span>-->
                <div class="custom-control custom-checkbox">
                    <input type="checkbox" class="custom-control-input" id="customCheck1" name="need">
                    <label class="custom-control-label" for="customCheck1">Необходимость</label>
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
                    <span>${part.id}</span>
                    <span>${part.name}</span>
                    <span>${part.need?string("yes", "no")}</span>
                    <span>${part.amount}</span>
                </div>
                <div class="card-footer text-muted">
                    ${part.ownerName}
                </div>
                <div>
                    <a class="btn btn-primary" href="parts/partEdit?partId=${part.id}">Edit</a>
                </div>
                <div>
                    <a class="btn btn-danger" href="parts/partDelete?partId=${part.id}">Delete</a>
                </div>
            </div>
        <#else >
            <div>No parts</div>
        </#list>
    </div>
    <@p.pager url page/>
</@c.page>