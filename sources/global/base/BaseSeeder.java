package global.base;


import providers.Logger;

public abstract class BaseSeeder<ModelType extends BaseModel, ModelService extends BaseService<ModelType>> {
	protected final Logger logger;
	protected final ModelService modelService;
	protected final ModelType[] models;

	protected BaseSeeder(Logger logger, ModelService modelService, ModelType[] models) {
		this.logger = logger;
		this.modelService = modelService;
		this.models = models;
	}

	public void seed() {
		this.logger.debug("Seed");

		for (ModelType model : this.models) {
			try {
				modelService.add(model);
			}
			catch (Exception e) {
				this.logger.error("Failed to seed: " + e.getMessage());
			}
		}
	}
}
