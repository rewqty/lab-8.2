package com.rewqty.lab82;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;

import com.rewqty.lab82.modules.Module;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class Application implements CommandLineRunner {

	private static Logger LOG = LoggerFactory
			.getLogger(Application.class);

	public static void main(String[] args) {
		LOG.info("STARTING THE APPLICATION");
		SpringApplication.run(Application.class, args);
		LOG.info("APPLICATION FINISHED");
	}

	@Override
	public void run(String... args) throws Exception {
		if (args.length != 1) {
			System.out.println("Неправильное количество аргументов.");
			return;
		}
		try {
			executeFunction(args[0]);
		} catch (FileNotFoundException e) {
			System.out.println("Файл не найден.");
		}
	}

	public void executeFunction(String filename) throws FileNotFoundException {
		File file = new File(filename);
		if (!file.exists()) {
			throw new FileNotFoundException();
		}
		ApplicationContext ctx = new AnnotationConfigApplicationContext("com.rewqty.lab82.modules");
		Module[] modules = getAvailableModules(file, ctx);
		if (modules.length == 0) {
			System.out.println("Нет модулей для этого файла.");
			return;
		}
		printAvailableModules(file, modules);
		int number = askUserForNumberOfFunction(modules.length);
		modules[number - 1].function(file);
	}

	private int askUserForNumberOfFunction(int max) {
		while (true) {
			System.out.printf("Введите номер функции (%d-%d): ", 1, max);
			String input = new Scanner(System.in).nextLine();
			int number;
			try {
				number = Integer.parseInt(input.strip());
				if (number < 1 || number > max) {
					System.out.println("Некорректный номер");
					continue;
				}
				return number;
			} catch (NumberFormatException e) {
				System.out.println("Некорректный номер");
			}
		}
	}

	private void printAvailableModules(File file, Module[] modules) {
		System.out.println("-----------------------------------------------------");
		System.out.println("Доступные функции для файла " + file.getAbsolutePath() + ":");
		for (int i = 0; i < modules.length; i++) {
			System.out.println((i + 1) + ". " + modules[i].getDescription());
		}
	}

	private Module[] getAvailableModules(File file, ApplicationContext ctx) {
		Map<String, Module> moduleMap = ctx.getBeansOfType(Module.class);
		return moduleMap.values()
				.stream()
				.filter(module -> module.isSupportedFormat(file))
				.toArray(Module[]::new);
	}
}
