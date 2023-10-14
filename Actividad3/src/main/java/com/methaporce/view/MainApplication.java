package com.methaporce.view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.methaporce.model.Movie;
import com.methaporce.model.MovieController;

public class MainApplication {
    private MovieController controller;
    private Scanner scanner;

    public enum Action {
        NewMovie,
        DeleteMovie,
        PrintAllMovies,
        PrintAvailableMovies,
        PrintUnavailableMovies,
        MarkMovieAsAvailable,
        AddDefaultMovies,
        Exit,
        InvalidValue,
    }

    public static void main(String[] args) {
        MainApplication app = new MainApplication();
        boolean is_running = true;
        app.initialize();

        while (is_running) {
            app.printOptionsMenu();
            Action action = app.waitForAction();
            switch (action) {
                case NewMovie:
                    app.addNewMovie();
                    break;
                case DeleteMovie:
                    app.deleteMovie();
                    break;
                case PrintAllMovies:
                    app.printAllMovies();
                    break;
                case PrintAvailableMovies:
                    app.printAvailableMovies();
                    break;
                case PrintUnavailableMovies:
                    app.printUnavailableMovies();
                    break;
                case MarkMovieAsAvailable:
                    app.markMovieAsAvailable();
                    break;
                case AddDefaultMovies:
                    app.addDefaultMovies();
                    break;
                case Exit:
                    is_running = false;
                    break;
                default:
                    System.out.println("Opcion invalida.");
                    break;
            }
        }

        System.out.println("Cerrando administrador.");
    }

    public void initialize() {
        controller = new MovieController();
        scanner = new Scanner(System.in);
        System.out.println("Bienvenido al administrador de peliculas.");
    }

    public void printOptionsMenu() {
        System.out.println("\nQue operacion desea realizar?");
        System.out.println("\t1.- Agregar una nueva pelicula.");
        System.out.println("\t2.- Eliminar una pelicula.");
        System.out.println("\t3.- Obtener todas la peliculas.");
        System.out.println("\t4.- Obtener las peliculas disponibles.");
        System.out.println("\t5.- Obtener las peliculas no disponibles.");
        System.out.println("\t6.- Marcar una pelicula como disponible.");
        System.out.println("\t7.- Crear una lista predeterminada de peliculas.");
        System.out.println("\t8.- Salir.\n");
        System.out.print("Opcion(#): ");
    }

    public int requestValue() {
        int value;
        try {
            value = scanner.nextInt();
        } catch (InputMismatchException e) {
            value = -1;
        }

        // clear buffer
        scanner.nextLine();
        return value;
    }

    public String requestString() {
        return scanner.nextLine();
    }

    public Action waitForAction() {
        switch (requestValue()) {
            case 1:
                return Action.NewMovie;
            case 2:
                return Action.DeleteMovie;
            case 3:
                return Action.PrintAllMovies;
            case 4:
                return Action.PrintAvailableMovies;
            case 5:
                return Action.PrintUnavailableMovies;
            case 6:
                return Action.MarkMovieAsAvailable;
            case 7:
                return Action.AddDefaultMovies;
            case 8:
                return Action.Exit;
            default:
                return Action.InvalidValue;
        }
    }

    public void addNewMovie() {
        System.out.print("Ingrese el ID de la pelicula: ");
        final int id = requestValue();

        System.out.print("Ingrese el nombre de la pelicula: ");
        final String name = requestString();

        if (id < 0) {
            System.out.println("Valor Invalido.");
            return;
        }

        if (name == null) {
            System.out.println("Nombre invalido.");
            return;
        }

        final boolean result = controller.addMovie(new Movie(id, name));

        if (!result) {
            System.out.println("Error, el ID de la pelicula ya esta en uso.");
            return;
        }

        System.out.println("Pelicula agregada correctamente.");
    }

    public void addDefaultMovies() {
        controller.addMovie(new Movie(0, "El padrino", true));
        controller.addMovie(new Movie(1, "Iron man", true));
        controller.addMovie(new Movie(2, "Los cazafantasmas", false));
        controller.addMovie(new Movie(3, "Terminator", true));
        controller.addMovie(new Movie(4, "El resplandor", false));
        controller.addMovie(new Movie(5, "Hercules", false));
        System.out.println("Peliculas agregadas corectamente.");
    }

    public void deleteMovie() {
        if (controller.getMovieCount() <= 0) {
            System.out.println("Error. No hay peliculas registradas en el sistema.");
            return;
        }

        System.out.print("Ingrese el ID de la pelicula: ");
        final int id = requestValue();

        if (id < 0) {
            System.out.println("Valor Invalido.");
            return;
        }

        final boolean result = controller.deleteMovie(id);

        if (!result) {
            System.out.println("Error, el ID solicitado no existe.");
            return;
        }

        System.out.println("Pelicula eliminada correctamente.");
    }

    public void markMovieAsAvailable() {
        System.out.print("Ingrese el ID de la pelicula: ");
        final int id = requestValue();

        if (id < 0) {
            System.out.println("Valor Invalido.");
            return;
        }

        final boolean result = controller.markMovieAsAvailable(id);

        if (!result) {
            System.out.println("Error, el ID solicitado no existe.");
            return;
        }

        System.out.println("Pelicula marcada como disponible correctamente.");
    }

    public void printAllMovies() {
        ArrayList<Movie> movies = controller.getMovies();

        if (movies.size() <= 0) {
            System.out.println("No hay peliculas disponibles registradas en el sistema.");
            return;
        }

        for (Movie movie : movies) {
            System.out.println(movie.toString());
        }
    }

    public void printAvailableMovies() {
        ArrayList<Movie> movies = controller.getAvailableMovies();

        if (movies.size() <= 0) {
            System.out.println("No hay peliculas no disponibles registradas en el sistema.");
            return;
        }

        for (Movie movie : movies) {
            System.out.println(movie.toString());
        }
    }

    public void printUnavailableMovies() {
        ArrayList<Movie> movies = controller.getUnavailableMovies();

        if (movies.size() <= 0) {
            System.out.println("No hay peliculas registradas en el sistema.");
            return;
        }

        for (Movie movie : movies) {
            System.out.println(movie.toString());
        }
    }
}
