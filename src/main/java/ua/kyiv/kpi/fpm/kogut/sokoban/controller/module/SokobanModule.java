package ua.kyiv.kpi.fpm.kogut.sokoban.controller.module;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.name.Names;
import ua.kyiv.kpi.fpm.kogut.sokoban.controller.Controller;
import ua.kyiv.kpi.fpm.kogut.sokoban.controller.EventListener;
import ua.kyiv.kpi.fpm.kogut.sokoban.model.*;
import ua.kyiv.kpi.fpm.kogut.sokoban.view.Field;
import ua.kyiv.kpi.fpm.kogut.sokoban.view.View;

/**
 * Created by i.kohut on 9/18/2017.
 */
public class SokobanModule extends AbstractModule {

    @Override
    protected void configure() {

        // init view factory
        install(new FactoryModuleBuilder()
                .implement(View.class, View.class)
                .build(View.ViewFactory.class));

        //init field factory
        install(new FactoryModuleBuilder()
                .implement(Field.class, Field.class)
                .build(Field.FieldFactory.class));

        bind(GameObjectFactory.class).to(GameObjectFactoryImpl.class);

        bind(String.class)
                .annotatedWith(Names.named("Path to properties file"))
                .toInstance("/resources/data.properties");

        bind(Integer.class)
                .annotatedWith(Names.named("Current level"))
                .toInstance(1);

        bind(EventListener.class).to(Controller.class);
    }
}