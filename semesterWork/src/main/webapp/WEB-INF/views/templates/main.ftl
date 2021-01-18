<#ftl encoding="UTF-8"/>
<#import "layouts/base.ftl" as base>
<@base.main title="Главная">
    <!--content-->
    <div class="content">
        <section class="page-section ">
            <div class="offer">
                <div class="container">
                    <div class="row">
                        <div class="col-md-8">
                            <div class="offer-cont">
                                <h1 class="offer-cont_title">
                                    <span> Всё обязательно будет хорошо </span>
                                    Я здесь, чтобы помочь Вам
                                </h1>
                                <p class="offer-cont_text">
                                    Моя магия безгранична
                                </p>
                                <button class="offer-cont_btn btn" onclick="location.href = '/services'">
                                    Подобрать услугу
                                </button>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <img src="/views/assets/main/header.png" class="img-fluid" alt="header image">
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <!--advantages-->
        <section class="page-section " id="advantages">
            <div class="container">
                <div class="adv-cont_title center">
                    <h2>Почему я?</h2>
                </div>
                <div class="row cards">
                    <div class="col md-4">
                        <div class="card-gradient">
                            <div class="card-body center ">
                                <p><h4 class="card-cont_title">Консультации онлайн</h4>
                                <div class="row">
                                    <div class="col">
                                        <h5 class="card-cont_text"><span> Если вам неудобно </span> приезжать в мой
                                            кабинет,
                                            мы
                                            можем встречаться онлайн.</h5>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col md-4">
                        <div class="card-gradient">
                            <div class="card-body center">
                                <p><h4 class="card-cont_title">Безупречная репутация</h4>
                                <div class="row ">
                                    <div class="col">
                                        <h5 class="card-cont_text">За время моей работы тысячи
                                            людей получили
                                            избавление от проблем, <span> горя и страдания.</span></h5>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col md-4">
                        <div class="card-gradient">
                            <div class="card-body center">
                                <p><h4 class="card-cont_title ">Цены ниже рыночных</h4>
                                <div class="row">
                                    <div class="col">
                                        <h5 class="card-cont_text ">Всегда стараюсь предоставить
                                            <span>своим клиентам
                                    самые</span> выгодные условия.</h5>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <!--stages-->
        <section class="page-section" id="stages">
            <div class="container">
                <div class="stages-cont_title center">
                    <h2>Этапы</h2>
                </div>
                <div class="row stages-cont_img">
                    <div class="offset-1 col-md-2">
                        <div class="row justify-content-center"><img src="/views/assets/main/1.png" alt="advantages">
                        </div>
                        <div class="row stages-cont_text center"><h6>1. Ваше обращение <span> ко мне </span></h6></div>
                    </div>
                    <div class="offset-2 col-md-2">
                        <div class="row justify-content-center"><img src="/views/assets/main/3.png" alt="advantages">
                        </div>
                        <div class="row stages-cont_text center"><h6>3. Заключение договора
                                <span> с последующей оплатой</span>
                                после
                                услуги
                            </h6>
                        </div>
                    </div>
                    <div class="offset-2 col-md-2 text-center">
                        <div class="row  justify-content-center"><img src="/views/assets/main/5.png" alt="advantages">
                        </div>
                        <div class="row stages-cont_text center"><h6> 5. Оплата любым удобным
                                <span>для вас способом</span>
                            </h6>
                        </div>
                    </div>
                </div>


                <div class="row stages-cont_img">
                    <div class="offset-3 col-md-2">
                        <div class="row justify-content-center"><img src="/views/assets/main/2.png" alt="advantages">
                        </div>
                        <div class="row stages-cont_text center"><h6>2. Выезд по указанному адресу или приезд <span>ко мне в
                    офис</span></h6>
                        </div>
                    </div>
                    <div class="offset-2 col-md-2">
                        <div class="row justify-content-center"><img src="/views/assets/main/4.png" alt="advantages">
                        </div>
                        <div class="row stages-cont_text center"><h6><span> 4. Организация и контроль</span> всего
                                процесса
                            </h6>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <!--services-->
        <section class="page-section" id="services">
            <div class="container">
                <div class="serv-cont_title center">
                    <h2>Чем я могу Вам помочь ?</h2>
                </div>
                <div class="row cards">
                    <#list categories as category>
                        <div class="col-md-4 center">
                            <img src="/views/assets/main/${category.getImage()}" alt="category">
                            <a href="/services">
                                <div class="text-services">${category.getName()}</div>
                            </a>
                        </div>
                    </#list>

                </div>

            </div>
            <div class="row cards">
                <div class="offset-3 col-md-6 center">
                    <button class="btn" onclick="location.href = '/services'">Перейти к полному списку услуг</button>
                </div>
            </div>
        </section>


        <#if user??>
        <#else>
            <section class="page-section" id="authorization">
                <div class="container">
                    <div class="row auth-cont_title center">
                        <div class="offset-1 col-md-4">
                            <a href="/auth"><h2>Авторизация</h2></a>
                        </div>
                        <div class="offset-2 col-md-3">
                            <a href="/register"><h2>Регистрация</h2></a>
                        </div>
                    </div>

                    <form class="form-signin" action="/auth" method="post">
                        <div class="row cards">
                            <div class="card border-white offset-3 col-md-6" style="border-radius: 20px">
                                <div class="card-body center">
                                    <div style="padding-top: 80px">
                                        <label for="inputEmail"></label>
                                        <input type="email" id="inputEmail" class="form-control auth-cont_input"
                                               placeholder="Почта" required name="email">
                                    </div>

                                    <div style="padding-top: 20px">
                                        <label for="inputPassword"></label>
                                        <input type="password" id="inputPassword" class="form-control auth-cont_input"
                                               placeholder="Пароль" required name="password">
                                    </div>
                                    <div style="padding-top:40px; padding-bottom: 40px">
                                        <div class="checkbox mb-3">
                                            <label style="color: black; font-family: 'Proxima Nova Rg', sans-serif">
                                                <input type="checkbox" value="remember-me" name="remember"> запомнить
                                                меня
                                            </label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row" style="padding-top: 50px">
                            <button class="offset-4 col-md-4 btn center" type="submit">Войти</button>
                        </div>
                    </form>
                </div>
            </section>
        </#if>
    </div>
</@base.main>
