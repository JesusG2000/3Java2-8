<#import "parts/common.ftl" as c>

<@c.page false>

</@c.page>
















<#--<@c.page false>
    &lt;#&ndash;<div class="form-row">
        <div class="form-group col-md-6">
            <form method="get" action="/main" class="form-inline">
                <input type="text" name="filter" placeholder="Search tag" value="${filter!}  ">
                <button type="submit" class="btn btn-primary ml-2">Search</button>
            </form>
        </div>
    </div>
    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"
       aria-controls="collapseExample">
        Add new message
    </a>

    <div class="collapse" id="collapseExample">
        <div class="form-group mt-3">
            <form action="/main" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <input type="text" class="form-control" name="text" placeholder="Введите сообщение"/>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="tag" placeholder="Тэг">
                </div>
                <div class="form-group">
                    <div class="custom-file">
                        <input type="file" name="file" id="customFile">
                        <label class="custom-file-label" for="customFile">Choose file </label>
                    </div>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary ml-2">Add</button>
                </div>
            </form>
        </div>
    </div>

    <#list messages as message>
        <div class="card my-3" style ="width: 18rem">
            <div class="card-img-top">
                <#if message.filename??>
                    <img src="/img/${message.filename}">
                </#if>
            </div>
            <div class="m-2">
            <span>${message.text}</span>
            <i>${message.tag}</i>
            <div class="card-footer text-muted">${message.authorName}</div>
            </div>
        </div>

    </#list>&ndash;&gt;
</@c.page>-->

