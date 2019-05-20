<#import "common.ftl" as c>

<@c.page>
    <div>
    <a class="btn btn-primary mb-2" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"
       aria-controls="collapseExample">
        Edit part
    </a>
    <div class="collapse" id="collapseExample">
        <div class="form-group mt-2">
            <form method="post" enctype="multipart/form-data">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <input type="hidden" name="id" value="<#if part??>${part.id}</#if>">
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
                <div class="custom-control custom-checkbox">
                    <input type="checkbox" class="custom-control-input" id="customCheck1" name="need">
                    <label class="custom-control-label" for="customCheck1">Необходимость</label>
                </div>
                <div class="custom-file">
                    <input class="custom-file-input" type="file" name="file" id="customFile">
                    <label class="custom-file-label col-sm-5" for="customFile">Choose file</label>
                </div>
                <button class="btn btn-primary mt-2" type="submit">Save</button>
            </form>
        </div>
    </div>
</@c.page>