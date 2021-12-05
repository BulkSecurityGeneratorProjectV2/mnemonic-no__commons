package no.mnemonic.commons.logging.log4j;

import no.mnemonic.commons.logging.Logger;
import no.mnemonic.commons.logging.LoggingContext;
import no.mnemonic.commons.logging.LoggingProvider;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

public class Log4JLoggingProvider implements LoggingProvider {

  private final Log4jLoggingContext loggingContext = new Log4jLoggingContext();

  @Override
  public Logger getLogger(String name) {
    return new Log4JLogger(name);
  }

  @Override
  public LoggingContext getLoggingContext() {
    return loggingContext;
  }

  public static class Log4JLogger implements Logger {

    private final org.apache.logging.log4j.Logger log4jLogger;

    public Log4JLogger(String name) {
      this.log4jLogger = LogManager.getLogger(name);
    }

    public void fatal(String formattedMessage, Object... args) {
      log(Level.FATAL, formattedMessage, args);
    }

    public void error(String formattedMessage, Object... args) {
      log(Level.ERROR, formattedMessage, args);
    }

    public void warning(String formattedMessage, Object... args) {
      log(Level.WARN, formattedMessage, args);
    }

    public void info(String formattedMessage, Object... args) {
      log(Level.INFO, formattedMessage, args);
    }

    public void debug(String formattedMessage, Object... args) {
      log(Level.DEBUG, formattedMessage, args);
    }

    public void fatal(Throwable ex, String formattedMessage, Object... args) {
      log(Level.FATAL, ex, formattedMessage, args);
    }

    public void error(Throwable ex, String formattedMessage, Object... args) {
      log(Level.ERROR, ex, formattedMessage, args);
    }

    public void warning(Throwable ex, String formattedMessage, Object... args) {
      log(Level.WARN, ex, formattedMessage, args);
    }

    public void info(Throwable ex, String formattedMessage, Object... args) {
      log(Level.INFO, ex, formattedMessage, args);
    }

    public void debug(Throwable ex, String formattedMessage, Object... args) {
      log(Level.DEBUG, ex, formattedMessage, args);
    }

    public boolean isDebug() {
      return log4jLogger.isDebugEnabled();
    }

    public boolean isInfo() {
      return log4jLogger.isInfoEnabled();
    }

    //private methods

    private synchronized void log(Level level, String message, Object... args) {
      if (message == null) return;
      if (args == null || args.length == 0) {
        //if no args, do not try to format this message
        log4jLogger.log(level, message);
      } else {
        log4jLogger.log(level, String.format(message, args));
      }
    }

    private synchronized void log(Level level, Throwable ex, String message, Object... args) {
      if (message == null) return;
      if (args == null || args.length == 0) {
        //if no args, do not try to format this message
        log4jLogger.log(level, message, ex);
      } else {
        log4jLogger.log(level, String.format(message, args), ex);
      }
    }

  }
}
