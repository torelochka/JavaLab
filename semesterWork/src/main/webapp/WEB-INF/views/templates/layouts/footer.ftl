<footer id="sticky-footer" class="foo text-white-50">
    <div class="container cont_foo">
        <div class="row">
            <div class="offset-1 col-md-3">
                <a href="/"><img src="/views/assets/user/crutch.png" alt="logo"></a>
            </div>
            <div class="offset-1 col-md-2">
                <div class="row"><a class="nav-link " href="/advantages">
                        <@spring.message 'header.advantages'/>
                    </a>
                </div>
                <div class="row"><a class="nav-link " href="/stages">
                        <@spring.message 'header.stages'/>
                    </a>
                </div>
                <div class="row"><a class="nav-link " href="/services">
                        <@spring.message 'header.services'/>
                    </a>
                </div>
                <#if user??>
                    <div class="row"><a class="nav-link " href="/profile">
                            <@spring.message 'header.profile'/>
                        </a>
                    </div>
                    <div class="row"><a class="nav-link " href="/basket">
                            <@spring.message 'header.basket'/>
                        </a>
                    </div>
                <#else>
                    <div class="row"><a class="nav-link " href="/signIn">
                            <@spring.message 'header.dropdown.user_action.sign_in'/>
                        </a>
                    </div>
                    <div class="row"><a class="nav-link " href="/signUp">
                            <@spring.message 'header.dropdown.user_action.sign_up'/>
                        </a>
                    </div>
                </#if>
            </div>
            <div class="offset-2 col-md-3">
                <div class="row">
                    <a href="tel:+79186663333" class="ml-auto phone"> +7 (918) 666-33-33</a>
                </div>
                <div class="row">
                    <a class="nav-link  ml-auto" href="/contact">
                        <@spring.message 'header.telephone_order'/>
                    </a>
                </div>
            </div>
        </div>
    </div>
</footer>
