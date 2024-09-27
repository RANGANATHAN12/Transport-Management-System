package com.demo.project.entity;
public class Duration{
    private long durationInSeconds; // Duration in seconds

    // Constructor
    public Duration(long durationInSeconds) {
        this.durationInSeconds = durationInSeconds;
    }

    // Getter for duration in seconds
    public long getDurationInSeconds() {
        return durationInSeconds;
    }

    // Setter for duration in seconds
    public void setDurationInSeconds(long durationInSeconds) {
        this.durationInSeconds = durationInSeconds;
    }

    // Convert duration to minutes
    public long getDurationInMinutes() {
        return durationInSeconds / 60;
    }

    // Convert duration to hours
    public long getDurationInHours() {
        return durationInSeconds / 3600;
    }

    // Convert duration to days
    public long getDurationInDays() {
        return durationInSeconds / 86400; // 86400 seconds in a day
    }

    // Format duration as a readable string (days, hours, minutes, seconds)
    public String formatDuration() {
        long days = getDurationInDays();
        long hours = getDurationInHours() % 24;
        long minutes = getDurationInMinutes() % 60;
        long seconds = durationInSeconds % 60;

        return String.format("%d days, %d hours, %d minutes, %d seconds", days, hours, minutes, seconds);
    }

    @Override
    public String toString() {
        return "DurationEntity{" +
               "durationInSeconds=" + durationInSeconds +
               ", formattedDuration='" + formatDuration() + '\'' +
               '}';
    }

    public static void main(String[] args) {
        // Example usage
        Duration duration = new Duration(123456); // Example duration in seconds
        System.out.println(duration);
        // Output: DurationEntity{durationInSeconds=123456, formattedDuration='1 days, 10 hours, 17 minutes, 36 seconds'}
    }
}
