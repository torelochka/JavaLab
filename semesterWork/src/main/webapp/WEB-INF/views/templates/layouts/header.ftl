<header class="header">
    <nav>
        <div class="container">
            <div class="row">
                <div class="col-md-2">
                    <a href="/main"><img src="/views/assets/main/logo.png" class="logo" alt="logo"></a>
                </div>
                <#if user??>
                    <div class="col-md-2 links">
                        <a class="nav-link " href="/advantages">Преимущества</a>
                    </div>
                    <div class="col-md-1 links">
                        <a class="nav-link " href="/stages">Этапы</a>
                    </div>
                    <div class="col-md-1 links">
                        <a class="nav-link " href="/services">Услуги</a>
                    </div>
                    <div class="col-md-1 links">
                        <a class="nav-link " href="/profile">Профиль</a>
                    </div>
                    <div class="col-md-1 links">
                        <a class="nav-link " href="/basket">Корзина</a>
                    </div>
                    <div class="offset-1 col-md-3 phone_top">
                        <div class="row">
                            <a href="tel:+79186663333" class="ml-auto phone"> +7 (918) 666-33-33</a>
                        </div>
                        <div class="row">
                            <a class="nav-link  ml-auto" href="/contact">Заказать
                                звонок</a>
                        </div>
                    </div>
                <#else>
                    <div class="offset-1 col-md-2 links">
                        <a class="nav-link " href="/advantages">Преимущества</a>
                    </div>
                    <div class="col-md-1 links">
                        <a class="nav-link " href="/stages">Этапы</a>
                    </div>
                    <div class="col-md-1 links">
                        <a class="nav-link " href="/services">Услуги</a>
                    </div>

                    <div class="dropdown show col-md-2 center links">
                        <a class="nav-link dropdown-toggle " href="" role="button" data-toggle="dropdown"
                           id="dropdownMenuLink"
                           aria-haspopup="true" aria-expanded="false">
                            Войти
                        </a>
                        <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                            <a class="dropdown-item nav-link" href="/signIn" >Вход</a>
                            <a class="dropdown-item nav-link" href="/signUp">Регистрация</a>
                        </div>
                    </div>

                    <div class="col-md-3 phone_top">
                        <div class="row">
                            <a href="tel:+79186663333" class="ml-auto phone"> +7 (918) 666-33-33</a>
                        </div>
                        <div class="row">
                            <a class="nav-link  ml-auto" href="/contact">Заказать
                                звонок</a>
                        </div>
                    </div>
                </#if>
            </div>
        </div>
    </nav>
</header>