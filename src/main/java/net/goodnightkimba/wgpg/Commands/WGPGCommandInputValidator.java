package net.goodnightkimba.wgpg.commands;

public class WGPGCommandInputValidator extends InputValidator {
	
	public boolean validatePolygonInput(String regionName, String radiusX, String radiusZ, String points, String offset, String minY, String maxY, String inputX, String inputZ, String world) throws UserInputException {
		validRegionName(regionName, world);
		validRadiusX(radiusX);
		validRadiusZ(radiusZ);
		validNumberOfPoints(points);
		validOffSet(offset);
		validMinY(minY);
		validMaxY(maxY);
		validRangeY(minY, maxY);
		validCentreX(inputX);
		validCentreZ(inputZ);
		validWorld(world);
		return true;
	}
	
	protected boolean validCentreZ(String input) throws UserInputException {
		if (!decimal(input)) throw new UserInputException("must-be-double", input, "double", "centerZ");
		
		return true;
	}

	protected boolean validWorld(String input) throws UserInputException {
		if (!worldName(input)) throw new UserInputException("invalid-world", input, "World Name", "world");
		return true;
	}

	protected boolean validCentreX(String input) throws UserInputException {
		if (!decimal(input)) throw new UserInputException("must-be-double", input, "double", "centerX");
		
		return true;
	}
	
	protected boolean validRegionName(String input, String world) throws UserInputException {
		if (!minLength(input, 1)) throw new UserInputException("too-short", input, "Region Name", "regionName");
		
		if (!maxLength(input, 255)) throw new UserInputException("too-long", input, "Region Name", "regionName");
		
		if (!regionName(input)) throw new UserInputException("invalid-region-name", input, "Region Name", "regionName");
		
		if (!regionExists(input, world)) throw new UserInputException("region-exists", input, "Region Name", "regionName");
		
		return true;	
	}

	protected boolean validMinY(String input) throws UserInputException {
		if (!integer(input)) throw new UserInputException("must-be-int", input, "integer", "minY");
		
		int minY = Integer.parseInt(input);
		
		if (!min(minY, 0)) throw new UserInputException("too-small", input, "2", "minY");
		
		if (!max(minY, 256)) throw new UserInputException("too-large", input, "256", "minY");
					
		return true;
	}
	
	protected boolean validMaxY(String input) throws UserInputException {
		if (!integer(input)) throw new UserInputException("must-be-int", input, "integer", "maxY");
		
		int maxY = Integer.parseInt(input);
		
		if (!min(maxY, 0)) throw new UserInputException("too-small", input, "0", "maxY");
		
		if (!max(maxY, 256)) throw new UserInputException("too-large", input, "256", "maxY");
					
		return true;
	}
	
	protected boolean validRangeY(String minY, String maxY) throws UserInputException {
		if (!validMinY(minY) || !validMaxY(maxY)) return false;
		
		if (!(Integer.parseInt(minY) <= Integer.parseInt(maxY))) throw new UserInputException("invalid-range", minY, maxY, "minY-maxY");
		
		return true;
	}

	protected boolean validRadiusX(String input) throws UserInputException {
		
		if (!integer(input)) throw new UserInputException("must-be-int", input, "integer", "radiusX");
		
		int radius = Integer.parseInt(input);
		
		if (!min(radius, 1)) throw new UserInputException("too-small", input, "1", "radiusX");
			
		return true;
	}
	
	protected boolean validRadiusZ(String input) throws UserInputException {
		
		if (!integer(input)) throw new UserInputException("must-be-int", input, "integer", "radiusZ");
		
		int radius = Integer.parseInt(input);
		
		if (!min(radius, 1)) throw new UserInputException("too-small", input, "1", "radiusZ");
			
		return true;
	}

	protected boolean validNumberOfPoints(String input) throws UserInputException {
		
		if (!integer(input)) throw new UserInputException("must-be-int", input, "integer", "numPoints");
		
		int numberPoints = Integer.parseInt(input);
		
		if (!min(numberPoints, 1)) throw new UserInputException("too-small", input, "1", "numPoints");
			
		if (!max(numberPoints, 360)) throw new UserInputException("too-large", input, "360", "numPoints");
			
		return true;
	}

	protected boolean validOffSet(String input) throws UserInputException {
		if (!integer(input)) throw new UserInputException("must-be-int", input, "integer", "offset");
		
		int offset = Integer.parseInt(input);	
		
		if (!min(offset, 0)) throw new UserInputException("too-small", input, "0", "offset");		
		
		if (!max(offset, 720)) throw new UserInputException("too-large", input, "720", "offset");	
		
		return true;
	}
}
