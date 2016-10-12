package ruby.keyboardwarrior.data.task;

import ruby.keyboardwarrior.data.exception.IllegalValueException;

/**
 * Represents a task's date in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidVenue(String)}
 */
public class Venue {

    public static final String EXAMPLE = "Clementi Mall";
    public static final String MESSAGE_VENUE_CONSTRAINTS = "Person venue can be in any format";
    public static final String VENUE_VALIDATION_REGEX = ".+";

    public final String venue;

    /**
     * Validates given venue.
     *
     * @throws IllegalValueException if given address string is invalid.
     */
    public Venue(String venue) throws IllegalValueException {
        if (!isValidVenue(venue)) {
            throw new IllegalValueException(MESSAGE_VENUE_CONSTRAINTS);
        }
        this.venue = venue;
    }

    /**
     * Returns true if a given string is a valid person email.
     */
    public static boolean isValidVenue(String test) {
        return test.matches(VENUE_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return venue;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Venue // instanceof handles nulls
                && this.venue.equals(((Venue) other).venue)); // state check
    }

    @Override
    public int hashCode() {
        return venue.hashCode();
    }

}
